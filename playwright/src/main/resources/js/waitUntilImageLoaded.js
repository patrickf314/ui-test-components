// Waits until an image is loaded
// noinspection BadExpressionStatementJS
/**
 * Checks if the given image node is loaded
 * The parameter in the argument is a flag whether to wait
 *
 * @param {HTMLImageElement} node the image element to check
 * @param {{timeout: number}} arg the argument
 * @returns {Promise<boolean>} the promise resolving to true, if the image was loaded successfully, false otherwise
 */
    (node, {timeout}) => new Promise((resolve, reject) => {
    let counter = timeout * 20;

    function checkImage() {
        if (node.complete) {
            resolve(node.naturalWidth > 0 && node.naturalHeight > 0);
            return;
        }

        counter--;
        if (counter === 0) {
            reject(new Error(`Image was not loaded in ${timeout} seconds.`));
        } else {
            setTimeout(checkImage, 50);
        }
    }

    checkImage();
})